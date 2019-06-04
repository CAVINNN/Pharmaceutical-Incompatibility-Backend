package com.pi.utils;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.tdb.TDBFactory;

public class CreateTDB {
	public static void main(String[] args){
		Dataset ds = TDBFactory.createDataset("./WebContent/tdb");
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		
		model.read("file:./WebContent/owl/PI_v3.0.owl");
		ds.addNamedModel("http://www.owl-ontologies.com/pi", model);
		
		model.close();
		ds.close();
		System.out.println("创建成功!");
	}
}
