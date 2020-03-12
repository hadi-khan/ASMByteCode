package org.utdallas.cs6367;

import java.lang.instrument.Instrumentation;

public class Agent {
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("I'm in premain!");
		
		//inst.addTransformer(transformer);
	}
}
