package org.yulia;


import org.drools.core.common.InternalAgenda;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import org.yulia.model.OrderItem;
import org.yulia.model.Result;

public class Main {
    public static void main(String[] args) {
        executeRule("org.yulia.agenda1", "org.yulia.agenda1.session", "Init-ag");
        executeRule("org.yulia.agenda2", "org.yulia.agenda2.session", "Init-ag");
        executeRule("org.yulia.plain1", "org.yulia.plain1.session", null);
        executeRule("org.yulia.plain2", "org.yulia.plain2.session", null);
    }

    private static void executeRule(String kbaseName, String ksessionName, String agendaGroup) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kcontainer = ks.getKieClasspathContainer();
        KieSession ksession = kcontainer.newKieSession(ksessionName);

        if (agendaGroup != null) {
            ((InternalAgenda) ksession.getAgenda()).getAgendaGroup(agendaGroup).setFocus();
        }


        ksession.setGlobal("$orderItem", new OrderItem(90.0));

        ksession.fireAllRules();
        if (ksession.getObjects().isEmpty()) {
            System.out.println(String.format("For kbase %s result is FALSE", kbaseName));
        } else {
            ksession.getObjects().stream().forEach(o -> {
                        if (o instanceof Result && ((Result) o).isOut())
                            System.out.println(String.format("For kbase %s result is TRUE", kbaseName));
                    }
            );
        }

        ksession.dispose();
    }


}