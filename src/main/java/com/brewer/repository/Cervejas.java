package com.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brewer.model.Cerveja;

@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long> {
	
	public Optional<Cerveja> findBySku(String sku);
	
//	public default Cerveja findBySkuTeste(String sku){
//		return manager.createQuery("select c from Cerveja c where lower(c.sku)like :sku")
//				.setParameter("sku", sku.toLowerCase())
//				.getSingleResult();
//	}
	
}
