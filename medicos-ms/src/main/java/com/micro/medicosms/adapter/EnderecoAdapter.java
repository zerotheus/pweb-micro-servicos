package com.micro.medicosms.adapter;

import com.micro.medicosms.form.EnderecoForm;
import com.micro.medicosms.model.Endereco;

public class EnderecoAdapter {
    
    private EnderecoForm endereco;

    public EnderecoAdapter(EnderecoForm endereco) {
        this.endereco = endereco;
    }

    public Endereco converte() {
        Endereco endereco = new Endereco();
        endereco.setBairro(this.endereco.getBairro());
        endereco.setCep(this.endereco.getCep());
        endereco.setCidade(this.endereco.getCidade());
        endereco.setComplemento(this.endereco.getComplemento());
        endereco.setUf(this.endereco.getUf());
        endereco.setNumero(this.endereco.getNumero());
        return endereco;
    }

}
