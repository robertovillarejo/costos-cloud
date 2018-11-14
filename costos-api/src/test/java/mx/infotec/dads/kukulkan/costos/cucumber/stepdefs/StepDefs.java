package mx.infotec.dads.kukulkan.costos.cucumber.stepdefs;

import mx.infotec.dads.kukulkan.costos.CostosapiApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = CostosapiApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
