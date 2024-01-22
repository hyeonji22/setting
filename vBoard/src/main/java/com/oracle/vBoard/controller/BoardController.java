package com.oracle.vBoard.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.vBoard.dto.Board;

import com.oracle.vBoard.service.BoardServicve;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
	

	private final BoardServicve boardservice;
		
	//목록
	@GetMapping("/board/{currentPage}")
	public ResponseEntity<Map<String, Object>> boardList(
	        Board board, @PathVariable int currentPage,
	        @RequestParam Map<String, String> inMap) {

	    int totalCnt = boardservice.totallist(board);
	    int pageSize =10;
	    int startIndex = (currentPage - 1) * pageSize;
	  
	    Map<String, Object> dataMap = new HashMap<>();
	    dataMap.put("keyword", inMap.get("keyword"));
	    dataMap.put("searchType", inMap.get("searchType"));
	    dataMap.put("startIndex", startIndex);
	    dataMap.put("pageSize", pageSize);
	    
	    
	    Map<String, Object> pageMaker = getPageMaker(currentPage, pageSize, totalCnt);

	    List<Board> boardList = boardservice.getBoardList(dataMap);

	    Map<String, Object> response = new HashMap<>();
	    response.put("dataMap", dataMap);
	    response.put("totalCnt", totalCnt);
	    response.put("boardList", boardList);
	    response.put("pageMaker", pageMaker);


        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
    //상세조회
    @GetMapping("/detail/{id}")
	public ResponseEntity<Board> getboardDetail(@PathVariable Long id){
		
		Board board = boardservice.getboardDetail(id);
		
		 if (board != null) {
			 return new ResponseEntity<>(board, HttpStatus.OK);
		 }else{
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
    //글등록
    @PostMapping("/write")
    public ResponseEntity<Integer> register(@RequestBody Board board){
    	
    	int result = boardservice.register(board);
    	
    	if (result == 1 ) {
			 return new ResponseEntity<Integer>(result, HttpStatus.OK);
		 }else{
			 return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
    	
    }
    //글수정
    @PatchMapping("/write/{id}")
    public ResponseEntity<Integer> update(@RequestBody Board board,@PathVariable Integer id){
    	
    	board.setId(id);
    	int result = boardservice.updateBoard(board);
    	
    	if (result == 1 ) {
			 return new ResponseEntity<Integer>(result, HttpStatus.OK);
		 }else{
			 return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
    }
  //글삭제
    @DeleteMapping("/write/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Long id){
    	
    	int result = boardservice.deleteBoard(id);
    	
    	if (result == 1 ) {
			 return new ResponseEntity<Integer>(result, HttpStatus.OK);
		 }else{
			 return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
    	
    }
    //페이징
    private Map<String, Object> getPageMaker(int currentPage, int amount, int totalCount) {
        Map<String, Object> pageMap = new HashMap<String, Object>();
        int endPage = (int)(Math.ceil(currentPage / 10.0) * 10);
        int startPage = endPage - 9;
        int realEnd = (int)(Math.ceil(totalCount * 1.0 /amount));
        endPage = realEnd < endPage ? realEnd : endPage;
        boolean prev = startPage > 1;
        boolean next = realEnd > endPage;
        int[] pageNumberArr = Arrays.copyOfRange(new int[endPage - startPage + 1], 0, endPage - startPage + 1);
          for (int i = 0; i < pageNumberArr.length; i++) {
             pageNumberArr[i] = startPage + i;
          }
        pageMap.put("endPage", endPage);
        pageMap.put("startPage", startPage);
        pageMap.put("prev", prev);
        pageMap.put("next", next);
        pageMap.put("currentPage", currentPage);
        pageMap.put("pageNumberArr", pageNumberArr);
        return pageMap;
     }
	
	
}
