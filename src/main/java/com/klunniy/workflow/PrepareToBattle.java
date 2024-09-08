package com.klunniy.workflow;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class PrepareToBattle implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // входная переменная вводимая пользователем:
        int warriors = (int) delegateExecution.getVariable("warriors");

        if (warriors < 0 || warriors > 100) {
            throw new BpmnError("warriors must be between 0 and 100");
        }

        // врага воины (это воины противника):
        int enemyWarriors = (int) (Math.random() * 100);
        String battleStatus = "Undefined";
        boolean isWin = false;

        if (warriors - enemyWarriors > 0) {
            isWin = true;
            battleStatus = "Victory";
        } else {
            battleStatus = "Fail :(";
        }

        delegateExecution.setVariable("enemyWarriors", enemyWarriors);
        delegateExecution.setVariable("battleStatus", battleStatus);
        delegateExecution.setVariable("isWin", isWin);
    }
}
