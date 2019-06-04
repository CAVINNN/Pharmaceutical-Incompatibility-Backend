package com.pi.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.tdb.TDBFactory;

public class Infer {
	public static void main(String[] args){
		
		Dataset tdb = TDBFactory.createDataset("./WebContent/tdb");
		Model model = tdb.getNamedModel("http://www.owl-ontologies.com/pi");
		
		List<Rule> rules = null;
		File file = new File("./WebContent/rules/PI_v5.0.rules");
		try {
			Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			rules = Rule.parseRules(Rule.rulesParserFromReader(br));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Reasoner reasoner = new GenericRuleReasoner(rules);
		InfModel inf = ModelFactory.createInfModel(reasoner, model);
		Model infModel = inf.getDeductionsModel();
		
		Iterator<?> deduct = infModel.listStatements();
		while (deduct.hasNext()) {
			System.out.println("--->" + deduct.next());
		}
		System.out.println("推理规则数：" + infModel.size());
		
		if( tdb.containsNamedModel("http://www.owl-ontologies.com/inferred") ) {
			System.out.println("TDB中已有\"inferred\" NamedGraph！");
		}
		else {
			tdb.addNamedModel("http://www.owl-ontologies.com/inferred", infModel);
			System.out.println("添加\"inferred\" NamedGraph成功！");
		}
		
	}
}
