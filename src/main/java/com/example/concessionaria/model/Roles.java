package com.example.concessionaria.model;

import java.util.Set;

public enum Roles {
    ADMIN(Set.of(Permissions.values())),

    DIRETOR(Set.of(
            Permissions.GERAR_RELATORIOS
    )),

    GERENTE_VENDAS(Set.of(
            Permissions.GERAR_RELATORIOS,
            Permissions.CRIAR_PROPOSTA,
            Permissions.APROVAR_PROPOSTA,
            Permissions.VIEW_VEICULOS
    )),

    VENDEDOR(Set.of(
            Permissions.CRIAR_PROPOSTA,
            Permissions.VIEW_VEICULOS
    )),

    GERENTE_POS_VENDA(Set.of(
            Permissions.ABRIR_OS,
            Permissions.EDITAR_OS,
            Permissions.FECHAR_OS,
            Permissions.GERAR_RELATORIOS
    )),

    MECANICO(Set.of(
            Permissions.EDITAR_OS,
            Permissions.FECHAR_OS
    )),

    ATENDENTE_OFICINA(Set.of(
            Permissions.ABRIR_OS,
            Permissions.EDITAR_OS
    )),

    FINANCEIRO(Set.of(
            Permissions.GERAR_RELATORIOS
    )),

    ESTOQUISTA(Set.of(
            Permissions.GERENCIAR_ESTOQUE,
            Permissions.VIEW_VEICULOS
    )),

    MARKETING(Set.of()),

    TI(Set.of(
            Permissions.GERENCIAR_USUARIOS
    )),

    CLIENTE(Set.of(
            Permissions.VIEW_VEICULOS
    ));

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissoess() {
        return permissions;
    }
}
