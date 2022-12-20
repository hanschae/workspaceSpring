package com.mulcam.myapp.dao;

import java.util.List;

import com.mulcam.myapp.vo.BoardVO;

public interface BoardDAO {
	// 글 등록
	public int boardWriteOk(BoardVO vo);
	// 글 목록
	public List<BoardVO> boardList();
	// 글 선택
	public BoardVO boardView(int no);
	// 조회수 증가
	public void hitCount(int no);
	// 글 수정
	public int boardEditOk(BoardVO vo);
	// 글 삭제
	public int boardDel(int no, String userid);
}
