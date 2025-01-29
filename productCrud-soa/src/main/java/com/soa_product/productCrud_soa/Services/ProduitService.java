package com.soa_product.productCrud_soa.Services;

import com.soa_product.productCrud_soa.Repository.ProduitRepository;
import com.soa_product.productCrud_soa.entitys.Produit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long id, Produit produitDetails) {
        Produit produit = produitRepository.findById(id).orElse(null);
        if (produit != null) {
            produit.setType(produitDetails.getType());
            produit.setReference(produitDetails.getReference());
            produit.setDescription(produitDetails.getDescription());
            produit.setPrix(produitDetails.getPrix());
            return produitRepository.save(produit);
        }
        return null;
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
