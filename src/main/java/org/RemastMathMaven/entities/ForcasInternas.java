package org.RemastMathMaven.entities;

import org.springframework.stereotype.Component;

@Component
public class ForcasInternas {

    private final ForcaPontual pontual;
    private ForcaDistribuida distribuida;
    private ForcaMomento momento;

    public ForcasInternas(ForcaPontual pontual){
        this.pontual = pontual;
    }

    public ForcasInternas(ForcaPontual pontual, ForcaDistribuida distribuida){
        this.pontual = pontual;
        this.distribuida = distribuida;
    }

    public ForcasInternas(ForcaPontual pontual, ForcaDistribuida distribuida, ForcaMomento momento){
        this.pontual = pontual;
        this.distribuida = distribuida;
        this.momento = momento;
    }
}
