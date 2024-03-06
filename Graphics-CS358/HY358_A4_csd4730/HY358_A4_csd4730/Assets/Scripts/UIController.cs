using UnityEngine;

/// <summary>
/// This scirpt contains functions that are called from UI Buttons.
/// They are assigned by navigating to the Button component,
/// assigning the object with this script to the slot of the "OnClick" field and choosing the appropriate function from the dropdown.
/// </summary>
public class UIController : MonoBehaviour
{
    GameObject canvas; // The reference to the canvas.

    void Start()
    {
        canvas = GameObject.Find("Canvas"); // Finding and assigning the reference to the canvas.
    }

    public void DestroyMenu() // Function responsible for destroying the whole canvas. Executed when "Start" is clicked.
    {
        Destroy(canvas);
    }

    public void Quit() // Function to quit the app. Executed when "Quit" is clicked. Works on build.
    {
        Application.Quit();
    }
}
