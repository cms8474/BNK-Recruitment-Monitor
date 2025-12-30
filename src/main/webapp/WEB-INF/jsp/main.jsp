<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="common/header.jsp" />

    <div class="text-center py-12">
        <h1 class="text-4xl font-bold text-gray-800 mb-4">BNK 채용 알리미</h1>
        <p class="text-lg text-gray-600 mb-8">BNK 시스템 채용 공고를 실시간으로 모니터링하여 알려드립니다.</p>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-4xl mx-auto">
            <div class="bg-white p-6 rounded-lg shadow-md border-t-4 border-primary">
                <h2 class="text-xl font-bold mb-2">실시간 모니터링</h2>
                <p class="text-gray-600">5분 간격으로 채용 사이트를 확인합니다.</p>
            </div>
            <div class="bg-white p-6 rounded-lg shadow-md border-t-4 border-primary">
                <h2 class="text-xl font-bold mb-2">이메일 알림</h2>
                <p class="text-gray-600">새로운 공고 발견 시 즉시 이메일로 발송됩니다.</p>
            </div>
        </div>

        <div class="mt-12">
            <form action="/recruit/crawl" method="post">
                <button type="submit"
                    class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-3 px-8 rounded-full shadow-lg transition duration-300 transform hover:scale-105 flex items-center justify-center mx-auto">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-2" fill="none" viewBox="0 0 24 24"
                        stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
                    </svg>
                    지금 확인하기
                </button>
            </form>
            <p class="mt-2 text-sm text-gray-500">클릭 시 즉시 크롤링을 수행하고 새로운 공고가 있으면 메일을 발송합니다.</p>
        </div>
    </div>

    <jsp:include page="common/footer.jsp" />