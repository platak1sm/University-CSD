using UnityEngine;

/// <summary>
/// This script can be attached to the Main Camera. The player variable must be assigned through the editor through drag-and-drop to the specified field.
/// If done correctly, the camera will follow the player object at a standard distance "offset".
/// </summary>
public class CameraController : MonoBehaviour
{
    public GameObject player; //The main player GameObject.
    private PlayerController pc;
    private Vector3 offset; // The distance of the camera from the player (sphere in our case).

    void Start()
    {
        offset = transform.position - player.transform.position; // Computing the distance from the player.
        pc = GameObject.Find("Sphere").GetComponent<PlayerController>();
    }


    void LateUpdate()
    {
        if (pc.win) transform.position = player.transform.position + offset/2;
        else transform.position = player.transform.position + offset; // Making the camera follow the player at a specific offset (the one we calculated above).
    }
}
