package com.klunniy.workflow;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PrepareToBattle implements JavaDelegate {

    @Value("${maxWarriors}")
    private int maxWarriors;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // входная переменная процесса вводимая пользователем:
        int warriors = (int) delegateExecution.getVariable("warriors");

        // врага воины (это число воины противника):
        int enemyWarriors = (int) (Math.random() * 100);

        maxWarriors = maxWarriors == 0 ? 100 : maxWarriors;

        if (warriors < 0 || warriors > 100) {
            throw new BpmnError("warriors must be between 0 and 100");
        }

        List<Boolean> army = new ArrayList<>(Collections.nCopies(warriors, true));

        System.out.println("PrepareToBattle! Enemy army: " + enemyWarriors + " vs. our army: " + warriors);
        delegateExecution.setVariable("army", army);
        delegateExecution.setVariable("enemyWarriors", enemyWarriors);


    }
}
