카카오 2023 상반기 안드로이드 기출문제

(코드는 Master Branch 확인하시면 됩니다!)

- 첫 번째 fragment : 검색 결과
    - [ ]  검색은 키워드 하나에 이미지 검색과 동영상 검색을 동시에 사용, 두 검색 결과를 합친 리스트를 사용합니다.
    - [ ]  동영상 검색은 API는 ([**링크**](https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video))의 thumbnail 필드를 사용합니다.
    - [ ]  두 검색 결과를 datetime 필드를 이용해 정렬하여 출력합니다. (최신부터 나타나도록)
    - [ ]  검색 결과 아이템에 [Image] 또는 [Video]를 표시합니다.
    - [ ]  검색 결과화면에서 마지막 결과로 스크롤시 다음 페이지가 자동 검색 되도록 구현합니다.(무한스크롤 기능)
    - [ ]  스크롤을 최상단으로 이동시키는 플로팅 액션 버튼을 추가합니다.
    - [ ]  아이템 선택시 SharedPreference에 저장합니다. (DB 사용 금지)

- 두 번째 fragment: 내 보관함
    - [ ]  SharedPreference에 저장된 리스트를 불러와 화면에 표시합니다.
    - [ ]  보관함에서 이미지 선택시 저장 리스트에서 삭제되며 화면에서도 삭제되도록 구현합니다.
 
- 사용기술: Bottom Navigation, Hilt를 이용한 Clean Architecture
