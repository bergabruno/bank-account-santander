package com.redbank.bankaccount.commons;

import com.redbank.bankaccount.model.collection.Usuario;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MongoListener extends AbstractMongoEventListener<Usuario> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Usuario> event) {
        LocalDate dataAtual = LocalDate.now();

        if (event.getSource().getDataCadastro() == null) {
            event.getSource().setDataCadastro(dataAtual);
        }

        super.onBeforeConvert(event);
    }
}
