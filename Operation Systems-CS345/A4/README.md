# Assignment 4: Implementation of the “Group Fairness” Scheduling Policy in the Linux Operating System
# Manos Platakis // csd4730


The scheduling policy was not implemented but here are some ideas that I tried to follow.

I tried to change the code in the files/functions you gave us as a hint and add some of my own in order to be as suitable as I could for the Group Fairness implementation.




**struct task_struct** {
        volatile long state;    /* -1 unrunnable, 0 runnable, >0 stopped */
        void *stack;
        atomic_t usage;
        unsigned int flags;     /* per process flags, defined below */
        unsigned int ptrace;

        int lock_depth;         /* BKL lock depth */
        char group_name;  /* proscess group name */ /*----------my change-------------*/
        int member_id;  /* member id of the group eg. 1 if A1*/ /*----------my change-------------*/
}

**struct rq** {
        /* runqueue lock: */
        raw_spinlock_t lock;

        /*
         * nr_running and cpu_load should be in the same cacheline because
         * remote CPUs use both these fields when doing load calculation.
         */
        unsigned long nr_running;
        #define CPU_LOAD_IDX_MAX 5
        unsigned long cpu_load[CPU_LOAD_IDX_MAX];
        unsigned long last_load_update_tick;
        int total_groups;  // Total number of groups in the system  /*----------my change-------------*/
        int *group_counts; // Array to store the number of tasks in each group /*----------my change-------------*/
        ...
}

**struct sched_entity** {
        struct load_weight      load;           /* for load-balancing */
        struct rb_node          run_node;
        struct list_head        group_node;
        unsigned int            on_rq 
        char                    group_name; /*----------my change-------------*/

        u64                     exec_start;
        u64                     sum_exec_runtime;
        u64                     vruntime;
        u64                     prev_sum_exec_runtime;

        u64                     nr_migrations;
...
}

**struct sched_class** {
        const struct sched_class *next;

        void (*enqueue_task) (struct rq *rq, struct task_struct *p, int flags);
        void (*dequeue_task) (struct rq *rq, struct task_struct *p, int flags);
        void (*yield_task) (struct rq *rq);

        void (*check_preempt_curr) (struct rq *rq, struct task_struct *p, int flags);

        struct task_struct * (*pick_next_task) (struct rq *rq);
        void (*put_prev_task) (struct rq *rq, struct task_struct *p);
        void (*task_new_group)(struct task_struct *p, int group); /*----------my change-------------*/
        void (*task_change_group)(struct task_struct *p, int new_group); /*----------my change-------------*/
#ifdef CONFIG_SMP
        int  (*select_task_rq)(struct rq *rq, struct task_struct *p,
                               int sd_flag, int flags);
...
}

 **static inline void context_switch(struct rq *rq, struct task_struct *prev, struct task_struct *next)**
{
        struct mm_struct *mm, *oldmm;

        prepare_task_switch(rq, prev, next);
        trace_sched_switch(prev, next);
        mm = next->mm;
        oldmm = prev->active_mm;
        /*
         * For paravirt, this is coupled with an exit in switch_to to
         * combine the page table reload and the switch backend into
         * one hypercall.
         */
        arch_start_context_switch(prev);

        if (!mm) {
                next->active_mm = oldmm;
                atomic_inc(&oldmm->mm_count);
                enter_lazy_tlb(oldmm, next);
        } else
                switch_mm(oldmm, mm, next);

        if (!prev->mm) {
                prev->active_mm = NULL;
                rq->prev_mm = oldmm;
        }

        if (prev->sched_class->task_change_group)
                prev->sched_class->task_change_group(prev, next->group); /*----------my change-------------*/

        if (next->sched_class->task_new_group)
                next->sched_class->task_new_group(next, next->group); /*----------my change-------------*/
 ...
}

**asmlinkage void __sched schedule(void)**
{
    struct task_struct *prev, *next;
    unsigned long *switch_count;
    struct rq *rq;
    int cpu;
    struct rq_flags rf;

    // existing code

    /*--------MYCODE_START----------*/

    number_of_groups = calculate_number_of_groups(); /* if not a standard number of groups got to be implemented dynamically*/

    rq = this_rq();
    prev = rq->curr;

//**To avoid starvation of the rest processes that do not belong in GF 80% of the time we will call the fair one, not 100%:**
    rand=random();
    if(rand<205){
        // Call your fair_pick_next_task function to select the next task
        next = pick_next_fair_task(rq, prev, &rf);
    }else{
        next= pick_next_task(rq);
    }

    //if pick_next_fair_task() returns null

    if (next == NULL){
        next= pick_next_task(rq);
    }

    // existing code for context switching, etc.

    // Set the current task to the selected next task
    rq->curr = next;
    context_switch(rq, prev, next);
    /*--------MYCODE_END----------*/

    // more existing code for context switching, etc.
}



//-------------------------------------------------------------------------------------------------------//


**/*--------------mycode--------------*/ in kernel/sched.c**

# Global variable to store group number
**int number_of_groups = 0;**

struct group_info {
    int group_id;
    int num_processes;
};

//Maintain an array of group_info structs
struct group_info groups[MAX_GROUPS];  //Assuming a maximum number of groups

//Function to calculate execution time
static int T(struct task_struct *p) {
    return 100 / number_of_groups / groups[p->group].num_processes;
}



# Normally, in the schedule() function of the sched.c file pick_next_task() function is called in order to pick the next task that will be executed.**

# I have created another function, called pick_next_fair_task() which looks like this: 


**static struct task_struct *pick_next_fair_task(struct rq *rq, struct task_struct *prev, struct rq_flags *rf)**
{
    struct sched_entity *se, *next_se;
    struct cfs_rq *cfs_rq;

    cfs_rq = &rq->cfs;

    if (unlikely(!cfs_rq->curr))
        return NULL;

    se = cfs_rq->curr;

    for_each_sched_entity(se) {
        // Your logic to calculate execution time based on group information
        int execution_time = T(se->task, rq->total_groups);

        if (execution_time > se->exec_start) {
            se->exec_start = execution_time;

            if (unlikely(!se->on_rq))
                resched_task(rq->curr);
        }
    }

    next_se = pick_next_entity(cfs_rq, NULL);

    if (next_se) {
        struct task_struct *next = task_of(next_se);

        if (next != rq->curr) {
            if (unlikely(on_rq_queued(next_se)))
                resched_task(rq->curr);
        }
        return next;
    }

    return NULL;
}