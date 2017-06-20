/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManager;
import entities.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author x2382383c
 */
@Stateless
public class StukemonEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

    public boolean insertTrainer(Trainer t) {
        if (!existsTrainer(t)) {
            EntityManager em = emf.createEntityManager();
            em.persist(t);
            em.close();
            return true;
        }
        return false;
    }

    public boolean existsTrainer(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Trainer trainerEncontrado = em.find(Trainer.class, t.getName());
        em.close();
        return trainerEncontrado != null;
    }

    public boolean insertPoke(Pokemon p) {
        if (!existsPoke(p)) {
            EntityManager em = emf.createEntityManager();
            em.persist(p);
            em.close();
            return true;
        }
        return false;
    }

    public Pokemon findPokemon(String name) {
        return (Pokemon) emf.createEntityManager().createNamedQuery("Pokemon.findByName").setParameter("name", name).getSingleResult();
    }

    public boolean existsPoke(Pokemon p) {
        EntityManager em = emf.createEntityManager();
        Pokemon pokeEncontrado = em.find(Pokemon.class, p.getName());
        em.close();
        return pokeEncontrado != null;
    }

    public boolean deletePoke(String name) {
        Pokemon p = findPokemon(name);
        EntityManager em = emf.createEntityManager();
        Pokemon pokeEncontrado = em.find(Pokemon.class, p.getName());
        if (existsPoke(pokeEncontrado)) {
            em.remove(pokeEncontrado);
            em.close();
            return true;
        }
        return false;
    }

    public List<Trainer> selectAllTrainersWithPokemons() {
        List<Trainer> listaTrainer = emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
        List<Trainer> todosTrainer = new ArrayList<>();
        for (Trainer actualTrainer : listaTrainer) {
            if (actualTrainer.getPokemonCollection().size() < 6) {
                todosTrainer.add(actualTrainer);
            }
        }
        return todosTrainer;
    }

    public Trainer findTrainer(String name) {
        return (Trainer) emf.createEntityManager().createNamedQuery("Trainer.findByName").setParameter("name", name).getSingleResult();
    }

    public List<Pokemon> listaPokemonRanking() {
        return emf.createEntityManager().createNamedQuery("Pokemon.findAllOrderRanking").getResultList();
    }

    public List<Trainer> listaEntrenadorRanking() {
        return emf.createEntityManager().createNamedQuery("Trainer.findAllOrderRanking").getResultList();
    }
}
