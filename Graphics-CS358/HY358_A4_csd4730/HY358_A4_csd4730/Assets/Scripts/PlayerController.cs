using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;

/// <summary>
/// This script can be attached to an object with a Rigidbody component.
/// It enables movement for this object, using the arrows from the keyboard, or the W,A,S,D buttons.
/// </summary>
public class PlayerController : MonoBehaviour
{
    public float speed = 250; // The movement speed. 
    public float jumpForce = 5f;
    int jumpsRemaining;
    private Rigidbody rb; // Reference to the Rigidbody component.
    public bool col = true; // blue = true , red = false
    public bool win = false;
    GameObject prefab,lifecanvas;
    public GameObject confettiPrefab; // Prefab of the confetti cube
    public int numberOfConfetti = 100; // Number of confetti cubes to spawn
    public int lives = 3;
    Vector3 initialposition;



    void Start()
    {
        rb = GetComponent<Rigidbody>(); // Assigning the reference of the Rigidbody.
        jumpsRemaining = 2;
        lifecanvas = GameObject.Find("Lives");
        initialposition = transform.position;
    }

    // FixedUpdate is called just before rendering
    void FixedUpdate()
    {
        float moveHorizontal = Input.GetAxis("Horizontal"); // Getting the input value from the keyboard arrows of the horizontal axis.
        float moveVertical = Input.GetAxis("Vertical"); // Getting the input value from the keyboad arros of the vertical axis.

        Vector3 movement = new Vector3(moveHorizontal, 0.0f, moveVertical); // Creating the movement vector based on the values we received above.

        rb.AddForce(movement * speed * speed); // Using the AddForce function of the Rigidbody component to move our object using the physics engine.
    }

    void Update()
    {
        // Check for jump input (for example, the space key)
        if (Input.GetKeyDown(KeyCode.Space))
        {
            // Call the Jump method to make the sphere jumps
            Jump();
        }
    }

    void Jump()
    {
        if (jumpsRemaining > 0)
        {
            rb.AddForce(Vector3.up * jumpForce, ForceMode.Impulse);
            jumpsRemaining--;
        }
    }

    void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.name.Contains("Platform"))
            jumpsRemaining = 2;
        else if (collision.gameObject.name.Contains("Enemy") || collision.gameObject.name == "Floor")
        {
            jumpsRemaining = 0;
            col = false;
            lives--;
            if (lives > 0)
            {
                lifecanvas.transform.GetChild(0).GetChild(0).GetComponent<TextMeshProUGUI>().text = "Lives: " + lives;
                Invoke("ReloadScene", 3);
            }
            else
            {
                Invoke("LoadMenu", 3);
            }
            
           
        }
        else if (collision.gameObject.name == "FloorEnd1")
        {
            Vector3 collisionPoint = collision.contacts[0].point;
            Vector3 spawnPosition = new Vector3(collisionPoint.x, collisionPoint.y + 30f, collisionPoint.z);

            // Spawn confetti cubes at the specified position
            SpawnConfetti(spawnPosition);
            win = true;
            prefab = Resources.Load<GameObject>("Prefabs/CongratsUI1");
            Instantiate(prefab);
            Invoke("LoadMenu", 5);
        }
        else if (collision.gameObject.name == "FloorEnd2")
        {
            Vector3 collisionPoint = collision.contacts[0].point;
            Vector3 spawnPosition = new Vector3(collisionPoint.x, collisionPoint.y + 30f, collisionPoint.z);

            // Spawn confetti cubes at the specified position
            SpawnConfetti(spawnPosition);
            win = true;
            prefab = Resources.Load<GameObject>("Prefabs/CongratsUI2");
            Instantiate(prefab);
            Invoke("LoadMenu", 5);
        }
    }

    void ReloadScene()
    {
        // Reload the current scene
        //SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
        transform.position = initialposition;
        col = true;
    }

    void LoadMenu()
    {
        SceneManager.LoadScene("Menu");
        win = false;
    }

    void SpawnConfetti(Vector3 spawnPosition)
    {
        for (int i = 0; i < numberOfConfetti; i++)
        {
            Quaternion spawnRotation = Quaternion.identity;
            GameObject confetti = Instantiate(confettiPrefab, spawnPosition, spawnRotation);

            // Set a random color for the confetti cube
            Renderer confettiRenderer = confetti.GetComponent<Renderer>();
            if (confettiRenderer != null)
            {
                confettiRenderer.material.color = UnityEngine.Random.ColorHSV();
            }
        }
    }
}
