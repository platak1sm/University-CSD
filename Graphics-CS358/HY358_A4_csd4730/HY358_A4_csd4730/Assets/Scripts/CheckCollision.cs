using UnityEngine;

/// <summary>
/// This script can be attached to a specific object. This object must have a non-trigger collider and a rigidbody component.
/// The function OnCollisionEnter will be triggered each time a collision is detected involving this object.
/// </summary>
public class CheckCollision : MonoBehaviour
{
    private void OnCollisionEnter(Collision collision) // Unity's built-in function to check whether a collision took place with this specific object.
    {
        Debug.Log(collision.gameObject.name); // Printing the name of the object that collided with the one this script is on.
        GetComponent<MeshRenderer>().material.color = Random.ColorHSV(); // Assigning a random color to the material of this specific object.
    }
}
