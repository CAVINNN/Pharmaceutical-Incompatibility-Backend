package com.pi.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.stereotype.Repository;

@Repository("instanceDao")
public class InstanceDaoImpl implements InstanceDao {

	@Override
	public List<String> getAllMedicines() {
		List<String> list = new ArrayList<String>();
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		String path = System.getProperty("evan.webapp") + "owl\\PI_v3.0.owl";
		model.read(path);
		Iterator<Individual> it = model.listIndividuals();
		while( it.hasNext() ){
			Individual individual = (Individual) it.next();
			list.add( individual.getLocalName() );
		}
		return list;
	}

	@Override
	public List<String> getChineseMedicines() {
		List<String> list = new ArrayList<String>();
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		String path = System.getProperty("evan.webapp") + "owl\\PI_v3.0.owl";
		model.read(path);
		Iterator<Individual> it = model.listIndividuals();
		while( it.hasNext() ){
			Individual individual = (Individual) it.next();
			OntClass ontclass = individual.getOntClass();
			if( !ontclass.isAnon() && ontclass.getLocalName().equals("中成药") ) {
				list.add( individual.getLocalName() );
			}
		}
		return list;
	}

	@Override
	public List<String> getWesternMedicines() {
		List<String> list = new ArrayList<String>();
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		String path = System.getProperty("evan.webapp") + "owl\\PI_v3.0.owl";
		model.read(path);
		Iterator<Individual> it = model.listIndividuals();
		while( it.hasNext() ){
			Individual individual = (Individual) it.next();
			OntClass ontclass = individual.getOntClass();
			if( !ontclass.isAnon() && ontclass.getLocalName().equals("西成药") ) {
				list.add( individual.getLocalName() );
			}
		}
		return list;
	}

}
