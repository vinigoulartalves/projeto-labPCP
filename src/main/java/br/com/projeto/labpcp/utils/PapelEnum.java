package br.com.projeto.labpcp.utils;

public enum PapelEnum {
    ADM("Administrador"),
    PEDAGOGICO("Pedagógico"),
    RECRUITER("Recrutador"),
    PROFESSOR("Professor"),
    ALUNO("Aluno");

    private final String descricao;

    PapelEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
