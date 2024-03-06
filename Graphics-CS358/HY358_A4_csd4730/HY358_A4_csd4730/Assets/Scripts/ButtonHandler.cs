using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ButtonHandler : MonoBehaviour
{
   // private GameObject menu;
    void Start()
    {
        //menu = GameObject.Find("Menu");
    }

    // Button click event handlers
    public void StartClick()
    {
        //Destroy(menu);
        string difficulty = GameObject.Find("Difficulty").transform.GetChild(0).GetComponent<TextMeshProUGUI>().text;
        if (difficulty.Contains("Easy")) SceneManager.LoadScene("Easy");
        else SceneManager.LoadScene("Medium");
    }

    public void DiffClick()
    {
        TextMeshProUGUI tmpui = GameObject.Find("Difficulty").transform.GetChild(0).GetComponent<TextMeshProUGUI>();
        string difficulty = tmpui.text;
        if (difficulty.Contains("Easy")) tmpui.text = "Difficulty: Medium" ;
        else tmpui.text = "Difficulty: Easy";
    }

    public void QuitClick()
    {
        Application.Quit();
    }
}