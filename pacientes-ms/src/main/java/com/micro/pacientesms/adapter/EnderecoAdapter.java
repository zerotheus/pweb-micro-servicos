package com.micro.pacientesms.adapter;

import com.micro.pacientesms.forms.EnderecoForm;
import com.micro.pacientesms.model.Endereco;

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
