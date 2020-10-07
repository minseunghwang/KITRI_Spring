package com.java.fileBoard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.fileBoard.dto.FileBoardDto;

public interface FileBoardDao {
	
	// 어노테이션 달아줄때 interface는 건들거없다리

	public int fileBoardGroupNumberMax();
	public int fileBoardWriteNumber(HashMap<String, Integer> hmap);
	public int fileBoardWriteOk(FileBoardDto fileBoardDto);
	public int fileBoardCount();
	public List<FileBoardDto> fileBoardList(int startRow, int endRow);
	public FileBoardDto fileBoardRead(int boardNumber);
	public FileBoardDto fileBoardSelect(int boardNumber);
	public int fileBoardDeleteOk(Map<String, Object> hmap);
	public int fileBoardUpdateOk(FileBoardDto upadteDto, int fileDelCheck);

}
