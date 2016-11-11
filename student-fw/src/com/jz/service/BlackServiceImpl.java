package com.jz.service;


import java.util.List;





import com.jz.bean.Black;
import com.jz.dao.BlackDAO;

public class BlackServiceImpl  implements BlackService {

	private BlackDAO bladao;


	
	public BlackDAO getBladao() {
		return bladao;
	}

	public void setBladao(BlackDAO bladao) {
		this.bladao = bladao;
	}

	@Override
	public List<Black> getAll() {
		// TODO Auto-generated method stub
		return  bladao.findAll();
	}

	@Override
	public Black findByName(String name) {
		// TODO Auto-generated method stub
		return bladao.findByName(name);
	}

	@Override
	public void saveBlack(Black black) {
		bladao.save(black);

	}

	@Override
	public void updateBlack(Black black) {
		bladao.merge(black);

	}

	@Override
	public List<Black> getInTable() {
		// TODO Auto-generated method stub
		return bladao.findIntable();
	}

	@Override
	public boolean isInBlack(String name) {
		Black bla=new Black();
		bla=bladao.findByName(name);

		int re=bla.getRemoved();
		if (re==0) {
			return true;
		}else
		return false;
	}

	public static void main(String[] args) {

		BlackService blas = new BlackServiceImpl();
//		List list=blas.getAll();
//
//		System.out.println(list);
//		Black black =new Black();
//		BlackDAO bl=new BlackDAO();
//		black=bl.findById(57);
//		black.setName("bbvv");
//		black.setRemoved(0);
		//blas.updateBlack(black);
		System.out.println(blas.findByName("6"));
		
	}

}
