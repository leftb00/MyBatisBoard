package com.leftb.mybatis.dao;

import java.util.ArrayList;

import com.leftb.mybatis.dto.BoardDto;

public interface BoardDao {

	public ArrayList<BoardDto> list();
	public BoardDto content(String bid);
	public void write(String bname, String btltle, String bcontent);
	public void modify(String bid, String btltle, String bcontent);
	public void delete(String bid);
	public void uphit(String bid);
}
