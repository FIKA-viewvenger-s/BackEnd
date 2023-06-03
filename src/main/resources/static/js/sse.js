const sse = new EventSource("http://localhost:8080/connect");

sse.addEventListener('connect', (e) => {
    // 반복문 : gameId 컴포넌트 찾고,
    // 찾은 컴포넌트 데이터를 전부 변경
    const { data: receivedConnectData } = e;
    console.log('connect event data: ', receivedConnectData);  // "connected!"
});