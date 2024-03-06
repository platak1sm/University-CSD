#include "std_lib_facilities.h"

struct Symptoms
{
    bool fever;
    bool breathingDifficulty;
    bool lossOfTasteOrSmell;
};

class Covid19Virus
{
protected:
    Symptoms symptoms;
    string mutationName;

public:
    virtual int AverageDaysDuration() = 0;
    virtual float TransmissionRate() = 0;

    Symptoms GetSymptoms();

    void SetSymptoms(Symptoms s);

    string GetInfo();
};

class CovidMutation1 : public Covid19Virus
{
public:
    CovidMutation1(string name);
    int AverageDaysDuration();
    float TransmissionRate();
};

class CovidMutation2 : public Covid19Virus
{
public:
    CovidMutation2(string name);
    int AverageDaysDuration();
    float TransmissionRate();
};