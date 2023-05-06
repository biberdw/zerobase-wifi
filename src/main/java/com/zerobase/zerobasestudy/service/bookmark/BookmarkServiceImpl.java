package com.zerobase.zerobasestudy.service.bookmark;

import com.zerobase.zerobasestudy.dto.bookmark.BookmarkDto;
import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.repository.bookmark.BookmarkRepository;
import com.zerobase.zerobasestudy.util.Sort;
import com.zerobase.zerobasestudy.util.constutil.DatabaseConst;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.*;

@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;

    /** 즐겨찾기 저장 */
    public void save(String name, Integer sequence) {

        Bookmark bookmark = Bookmark.builder()
                .name(name)
                .sequenceNum(sequence)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();
        bookmarkRepository.save(bookmark);
    }


    public BookmarkDto.Response getBookmarkDto(Long id) {
        Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 북마크 id= " + id));
        return new BookmarkDto.Response(bookmark);
    }

    /** 북마크 순위 정렬 리스트 */
    public List<BookmarkDto.Response> getDtoListOrderBySeq() {
        Sort sort = new Sort(SEQUENCE_NUM, Sort.Direction.ASC);
        return bookmarkRepository.findAll(null, sort).stream()
                .map(BookmarkDto.Response::new).collect(Collectors.toList());
    }

    /** 와이파이가 등록된 북마크 제외한 전체리스트 */
    public List<BookmarkDto.Response> getDtoListExcludingWifi(Long wifiId) {
        Sort sort = new Sort(SEQUENCE_NUM, Sort.Direction.ASC);
        return bookmarkRepository.findAllExcludingWifi(wifiId, null, sort).stream()
                .map(BookmarkDto.Response::new).collect(Collectors.toList());
    }

    /** 북마크 수정 */
    public void update(Long id, String name, Integer sequenceNum) {
        Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 북마크 id= " + id));

        //둘중에 하나라도 변경이 있을 때
        if(!bookmark.getName().equals(name) || bookmark.getSequenceNum() != sequenceNum){
                bookmarkRepository.update(id, null, sequenceNum);

            //이름이 변경되고 순서는 같을 때
        }else if(!bookmark.getName().equals(name) && bookmark.getSequenceNum() == sequenceNum)  {
                bookmarkRepository.update(id, name, null);
            //둘다 변경 됐을 때
        }else  {
            bookmarkRepository.update(id, name, sequenceNum);
        }
    }


    public void delete(Long id) {
        Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 즐겨찾기 입니다. id =" + id));

        bookmarkRepository.deleteById(bookmark.getId());
    }

    /** 닉네임 중복조회 */
    public boolean existByName(String name, Long id) {
        if(id != null ){
            Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 즐겨찾기"));
            if (bookmark.getName().equals(name)){
                return false;
            }
        }
        return bookmarkRepository.findByName(name);
    }
}
