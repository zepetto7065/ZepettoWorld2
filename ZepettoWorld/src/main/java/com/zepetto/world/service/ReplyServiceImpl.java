package com.zepetto.world.service;
import java.util.List;

import javax.inject.Inject;

import com.zepetto.world.domain.Criteria;
import com.zepetto.world.domain.ReplyVO;
import com.zepetto.world.persistence.BoardDAO;
import com.zepetto.world.persistence.ReplyDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class ReplyServiceImpl implements ReplyService {

	
	private final String NAMESPACE = "kr.co.ca.ReplyMapper";

	private final ReplyDAO replyDAO;
	private final BoardDAO boardDAO;
	
	@Inject
	public ReplyServiceImpl(ReplyDAO replyDAO, BoardDAO boardDAO) {
		// TODO Auto-generated constructor stub
		this.replyDAO = replyDAO;
		this.boardDAO = boardDAO;
	}
	
	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return replyDAO.list(bno);
	}

	@Transactional
	@Override
	public void create(ReplyVO replyVO) throws Exception {
		replyDAO.create(replyVO);
		boardDAO.updateReplyCnt(replyVO.getBno(), 1); // 댓글 갯수 증가
	}

	@Override
	public void update(ReplyVO replyVO) throws Exception {
		replyDAO.update(replyVO);
	}

	@Transactional
	@Override
	public void delete(Integer replyNo) throws Exception {
		int bno = replyDAO.getBno(replyNo);
		replyDAO.delete(replyNo);
		boardDAO.updateReplyCnt(bno, -1); // 댓글 갯수 감소
	}

	@Override
	public List<ReplyVO> listPaging(Integer bno, Criteria criteria) throws Exception {
		return replyDAO.listPaging(bno,criteria);
	}

	@Override
	public int countReplies(Integer bno) throws Exception {
		return replyDAO.countReply(bno);
	}




}
