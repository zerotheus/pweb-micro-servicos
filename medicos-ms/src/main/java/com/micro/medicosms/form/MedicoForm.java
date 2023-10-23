package com.micro.medicosms.form;

import jakarta.validation.Valid;

public class MedicoForm {

    @Valid
    private DadosCadastraisForm dadosCadastrais;
    @Valid
    private EnderecoForm endereco;

}
