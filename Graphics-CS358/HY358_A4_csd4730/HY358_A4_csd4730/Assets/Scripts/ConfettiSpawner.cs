//using System;
//using System.Collections;
//using System.Collections.Generic;
//using System.Data.SqlTypes;
//using System.Linq;
//using UnityEditor;
//using UnityEngine;


//public class ConfettiSpawner : MonoBehaviour
//{
//    public GameObject confettiPrefab; // Prefab of the confetti cube
//    public int numberOfConfetti = 100; // Number of confetti cubes to spawn

//    void OnCollisionEnter(Collision collision)
//    {
//        if (collision.gameObject.name=="FloorEnd")
//        {
//            Vector3 collisionPoint = collision.contacts[0].point;
//            Vector3 spawnPosition = new Vector3(collisionPoint.x, collisionPoint.y + 30f, collisionPoint.z);

//            // Spawn confetti cubes at the specified position
//            SpawnConfetti(spawnPosition);
//        }
//    }

//    void SpawnConfetti(Vector3 spawnPosition)
//    {
//        for (int i = 0; i < numberOfConfetti; i++)
//        {
//            Quaternion spawnRotation = Quaternion.identity;
//            GameObject confetti = Instantiate(confettiPrefab, spawnPosition, spawnRotation);

//            // Set a random color for the confetti cube
//            Renderer confettiRenderer = confetti.GetComponent<Renderer>();
//            if (confettiRenderer != null)
//            {
//                confettiRenderer.material.color = UnityEngine.Random.ColorHSV();
//            }
//        }
//    }
//}