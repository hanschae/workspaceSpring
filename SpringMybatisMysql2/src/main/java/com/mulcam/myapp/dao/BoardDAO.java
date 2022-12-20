package com.mulcam.myapp.dao;

import java.util.List;

import com.mulcam.myapp.vo.BoardVO;

public interface BoardDAO {
	// �� ���
	public int boardWriteOk(BoardVO vo);
	// �� ���
	public List<BoardVO> boardList();
	// �� ����
	public BoardVO boardView(int no);
	// ��ȸ�� ����
	public void hitCount(int no);
	// �� ����
	public int boardEditOk(BoardVO vo);
	// �� ����
	public int boardDel(int no, String userid);
}
