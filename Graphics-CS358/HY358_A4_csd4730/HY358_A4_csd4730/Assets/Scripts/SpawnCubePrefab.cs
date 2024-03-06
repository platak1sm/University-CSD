using UnityEngine;

/// <summary>
/// This script can be attached to any object. When Z is pressed on the keyboard, a cube will be spawned. 
/// </summary>
public class SpawnCubePrefab : MonoBehaviour
{
    GameObject Cube; // Reference to the cube that will be spawned.

    void Start()
    {
        Cube = Resources.Load("Prefabs/MetalCube") as GameObject; // Loading the cube from the resources folder.
    }

    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Z)) // Checking if Z button is pressed.
        {
            GameObject a = Instantiate(Cube); // Spawning the cube prefab.
        }
    }
}
