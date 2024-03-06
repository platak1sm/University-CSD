#include "std_lib_facilities.h"
#include "askisi4.h"

Symptoms Covid19Virus::GetSymptoms()
{
    return symptoms;
}

void Covid19Virus::SetSymptoms(Symptoms s)
{
    symptoms = s;
}

string Covid19Virus::GetInfo()
{
    cout << "<Mutation: " << mutationName << ", Fever: " << std::boolalpha << symptoms.fever << ", BreathingDifficulty: " << std::boolalpha << symptoms.breathingDifficulty
         << ", LossOfTasteOrSmell: " << std::boolalpha << symptoms.lossOfTasteOrSmell << ", AverageDaysDuration: " << AverageDaysDuration()
         << " days, TransmissionRate: " << TransmissionRate() << ">" << endl;
}

CovidMutation1::CovidMutation1(string name)
{
    mutationName = name;
};

int CovidMutation1::AverageDaysDuration()
{
    int ss = 10;
    if (symptoms.fever == 1)
        ss += 5;
    if (symptoms.breathingDifficulty == 1)
        ss += 7;
    if (symptoms.lossOfTasteOrSmell == 1)
        ss += 2;
    return ss;
}

float CovidMutation1::TransmissionRate()
{
    float sr = 0;
    if (symptoms.fever == 1)
        sr += 0.2;
    if (symptoms.breathingDifficulty == 1)
        sr += 0.05;
    if (symptoms.lossOfTasteOrSmell == 1)
        sr += 0.15;
    return sr;
}

CovidMutation2::CovidMutation2(string name)
{
    mutationName = name;
};

int CovidMutation2::AverageDaysDuration()
{
    int ss = 5;
    if (symptoms.fever == 1)
        ss += 8;
    if (symptoms.breathingDifficulty == 1)
        ss += 2;
    if (symptoms.lossOfTasteOrSmell == 1)
        ss += 1;
    return ss;
}

float CovidMutation2::TransmissionRate()
{
    float sr = 0;
    if (symptoms.fever == 1)
        sr += 0.25;
    if (symptoms.breathingDifficulty == 1)
        sr += 0.08;
    return sr;
}

int main()
{
    string name;
    int f;
    int b;
    int ts;
    vector<Covid19Virus *> v;
    Symptoms s;
    ofstream ofs("myfile");
    for (;;)
    {
        for (;;)
        {
            cout << "Give the type of mutation(CovidMutation1 or CovidMutation2): (give <<quit>> if you want to stop)" << endl;
            cin >> name;
            if ((name == "CovidMutation1") || (name == "CovidMutation2") || (name == "quit"))
                break;
        }
        if (name == "quit")
            break;

        cout << "Give the symptoms: " << endl;
        for (;;)
        {
            cout << "Fever: (answer with 1 (true) or 2 (false))" << endl;
            cin >> f;
            if ((f == 1) || (f == 2))
                break;
        }
        for (;;)
        {
            cout << "Breathing Difficulty: (answer with 1 (true) or 2 (false))" << endl;
            cin >> b;
            if ((b == 1) || (b == 2))
                break;
        }
        for (;;)
        {
            cout << "Loss of taste or smell: (answer with 1 (true) or 2 (false))" << endl;
            cin >> ts;
            if ((ts == 1) || (ts == 2))
                break;
        }
        s.fever = f;
        s.breathingDifficulty = b;
        s.lossOfTasteOrSmell = ts;

        if (name == "CovidMutation1")
        {
            CovidMutation1 m1(name);
            m1.SetSymptoms(s);
            v.push_back(&m1);
        }
        else if (name == "CovidMutation2")
        {
            CovidMutation2 m2(name);
            m2.SetSymptoms(s);
            v.push_back(&m2);
        }
    }
    const size_t s1 = v.size();
    for (int i = 0; i < s1; i++)
    {
        ofs << v[i]->GetInfo();
    }
    return 0;
}
