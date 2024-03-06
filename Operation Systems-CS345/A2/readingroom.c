#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <time.h>

#define MAX_STUDENTS 40
#define MAX_READING_ROOM_CAPACITY 8
#define MIN_STUDY_TIME 5
#define MAX_STUDY_TIME 15

// define mutex 
pthread_mutex_t eight_person_mutex;
//define semaphores
sem_t room_mutex;

int groups[40];
int grcount=0;
//student struct
typedef struct {
    int id;
    int study_time;
} Student;

int reading_room_count = 0;


// simple queue 
typedef struct Node {
    Student student;
    struct Node* next;
} Node;

Node* waiting_queue = NULL;

void enqueue(Student student) {
    Node* new_node = (Node*)malloc(sizeof(Node));
    new_node->student = student;
    new_node->next = NULL;

    if (waiting_queue == NULL) {
        waiting_queue = new_node;
    } else {
        Node* current = waiting_queue;
        while (current->next != NULL) {
            current = current->next;
        }
        current->next = new_node;
    }
}

Student dequeue() {
    if (waiting_queue == NULL) {
        Student dummy;
        dummy.id = -1;
        return dummy;
    }

    Node* front = waiting_queue;
    Student student = front->student;
    waiting_queue = waiting_queue->next;
    free(front);
    return student;
}

// Function to print the current condition of the rooms
void print_rooms() {
    // printf("\nReading Room: | ");
    // for (int i = 1; i <= reading_room_count; i++) {
    //     int student_id = groups[(i - 1) + (8 * grcount)];
    //     printf("%d | ", student_id);
    // }
    // for (int i = reading_room_count + 1; i <= MAX_READING_ROOM_CAPACITY; i++) {
    //     printf("| ");
    // }
    // printf("\nWaiting Room: | ");
    // Node* current = waiting_queue;
    // while (current != NULL) {
    //     printf("%d | ", current->student.id);
    //     current = current->next;
    // }
    // printf("\n\n");
}




//function to simulate a student reading
void *student_thread(void *arg) {
    Student *student = (Student *)arg;
    int count=0;
    printf("Student %d was born\n", student->id);
    groups[count++]= student->id;
    // handle the priority of the students waiting
    if (reading_room_count >= 8) {
        printf("Student %d cannot enter the study room. It is full.\n", student->id);
        
        enqueue(*student);
        print_rooms();
        
        while (student->id != waiting_queue->student.id) {
            sleep(1);  // Sleep for a short time to avoid busy-waiting
        }
        
        //printf("Student %d is the next student to enter\n", student->id);
    }

    // the last one of the 8 to enter locks
    if (reading_room_count >= 7) {
        //printf("%d entered lock with count %d\n", student->id, reading_room_count);
        pthread_mutex_lock(&eight_person_mutex);
        //printf("%d passed lock with count %d\n", student->id, reading_room_count);
    }

    // in case the first one of the 8 to pass has the eight person lock give it up(unlock)
    if (reading_room_count == 0) {
        pthread_mutex_unlock(&eight_person_mutex);
        
        //printf("%d is first to go in, give up lock %d\n", student->id, reading_room_count);
    }

    // each time a thread passes this point it decreases the semaphores value by 1
    // the starting value is 8, when the 8 first threads pass, the value will be 0 and it will lock
    sem_wait(&room_mutex);
    
    reading_room_count++;
    
    // if the thread that entered came from the waiting queue, remove it from queue 
    if (waiting_queue != NULL)
        dequeue();

    
    printf("Student %d entered the reading room\n", student->id);
    print_rooms();
    
    //simulate reading
    sleep(student->study_time);

    // increases the semaphore value by 1 giving place for a thread that is waiting to pass
    sem_post(&room_mutex);
    reading_room_count--;
    
    // the last one of the 8 to leave unlocks
    if (reading_room_count == 0) {
        pthread_mutex_unlock(&eight_person_mutex);
        grcount++;
    }

    printf("Student %d exited the reading room after studying for %d seconds\n", student->id, student->study_time);
}

int main() {
    srand(time(NULL));

    int num_students;
    printf("Please enter the number of students N: ");
    scanf("%d", &num_students);

    // init semaphore with a value of 8 for the 8 free places inside of the reading room
    // this means that 8 threads can enter the lock before it locks
    sem_init(&room_mutex, 0, 8);

    // init mutex, it's locked by the last person to enter and it is unlocked by the last person to leave
    // the first 8 enter, last one to enter locks, all 8 leave, last one to leave unlocks
    // each time 8 enter, and after all 8 are done the next 8 enter. 
    pthread_mutex_init(&eight_person_mutex, NULL);

    pthread_t student_threads[num_students];
    Student students[num_students];

    for (int i = 0; i < num_students; i++) {
        students[i].id = i + 1;
        students[i].study_time = (rand() % (MAX_STUDY_TIME - MIN_STUDY_TIME + 1)) + MIN_STUDY_TIME;
        pthread_create(&student_threads[i], NULL, student_thread, &students[i]);
    }

    for (int i = 0; i < num_students; i++) {
        pthread_join(student_threads[i], NULL);
    }

    sem_destroy(&room_mutex);
    pthread_mutex_destroy(&eight_person_mutex);
    return 0;
}






