using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DynamicMaterial: MonoBehaviour
{
    public Material sphereMaterial; // Assign the material in the Unity Editor
    public PlayerController pc;
    private Color newColor;
    private void Start()
    {
        pc = GameObject.Find("Sphere").GetComponent<PlayerController>();
    }
    void Update()
    {
        // Example: Change the color based on time
        
        float lerpValue = Mathf.PingPong(Time.time, 1f);

        if (pc.col)
            newColor = Color.Lerp(Color.black, Color.blue, lerpValue);
        else
            newColor = Color.Lerp(Color.black, Color.red, lerpValue);

        // Set the color property of the material
        sphereMaterial.SetColor("_Color", newColor);
    }
}