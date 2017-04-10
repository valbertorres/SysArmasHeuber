package com.heuber.interfacePO;

import java.util.List;

public interface InterfacePO<T> {

	public void salvar() throws Exception;

	public void atualizar() throws Exception;

	public void deletar() throws Exception;

	public List<T> select() throws Exception;

}
