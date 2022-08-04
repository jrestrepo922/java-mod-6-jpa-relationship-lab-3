package org.example;

import org.example.models.Cat;
import org.example.models.Owner;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        // create cats
        Cat cat1 = new Cat();
        cat1.setName("Tasha");
        cat1.setAge(7);
        cat1.setBreed("Mutt");

        Cat cat2 = new Cat();
        cat2.setName("Coco");
        cat2.setAge(4);
        cat2.setBreed("Poodle");

        // create owner
        Owner owner1 = new Owner();
        owner1.setName("Juan");

        Owner owner2 = new Owner();
        owner2.setName("Susan");

        // Create the relationship
        owner1.addCat(cat1);
        owner1.addCat(cat2);
        owner2.addCat(cat1);
        owner2.addCat(cat2);

        cat1.addOwner(owner1);
        cat1.addOwner(owner2);
        cat2.addOwner(owner1);
        cat2.addOwner(owner2);


        // create EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // access transaction object
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(owner1);
        entityManager.persist(owner2);
        entityManager.persist(cat1);
        entityManager.persist(cat2);
        transaction.commit();

        // close entity manager
        entityManager.close();
        entityManagerFactory.close();
    }
}