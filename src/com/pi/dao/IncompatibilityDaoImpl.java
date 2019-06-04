package com.pi.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.tdb.TDBFactory;
import org.springframework.stereotype.Repository;

@Repository("incompatibilityDao")
public class IncompatibilityDaoImpl implements IncompatibilityDao {

	@Override
	public List<String> getIncompatibilityMedicines(String medicine) {
		List<String> list = new ArrayList<String>();
		
		String path = System.getProperty("evan.webapp") + "tdb";
		Dataset tdb = TDBFactory.createDataset(path);
		Model model = tdb.getNamedModel("http://www.owl-ontologies.com/inferred");
		String queryString = 
				  "PREFIX pi:<http://www.owl-ontologies.com/OntologyPI.owl#>"
				+ "SELECT ?o"
				+ "{ pi:" + medicine + " pi:禁忌 ?o }";
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		while( results.hasNext() ){
			QuerySolution temp = results.nextSolution();
			RDFNode subjectNode = temp.get("o");
			list.add( subjectNode.asResource().getLocalName() );
		}
		
		return list;
	}

}
