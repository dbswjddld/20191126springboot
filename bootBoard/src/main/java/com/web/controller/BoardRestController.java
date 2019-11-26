package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.Board;
import com.web.repository.BoardRepository;


@RestController
@RequestMapping("/api/boards")
public class BoardRestController {

	@Autowired
	BoardRepository boardRepository;
	
	// 등록
	@PostMapping // post 요청
	public ResponseEntity<?> postBoard(@RequestBody Board board) {
		board.setCreateDateNow();
		System.out.println("--------" + board);
		boardRepository.save(board);
		return new ResponseEntity<>("{}", HttpStatus.CREATED);
	}
	
	// 수정
	@PutMapping("/{idx}")
	public ResponseEntity<?> putBoard(@PathVariable("idx") Long idx,
										@RequestBody Board board) {
		Board persisBoard = boardRepository.getOne(idx);
		persisBoard.setUpdateDateNow();
		persisBoard.update(board);
		boardRepository.save(persisBoard);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("{idx}")
	public ResponseEntity<?> deleteBoard(@PathVariable("idx") Long idx) {
		Board persistBoard = boardRepository.getOne(idx);
		boardRepository.deleteById(idx);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
}
