<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <jsp:include page="../common/header.jsp" />

        <div class="space-y-8">
            <!-- Section A: Recruitment List -->
            <section>
                <h2 class="text-2xl font-bold mb-4 border-l-4 border-primary pl-3">현재 진행중인 공고</h2>
                <div class="bg-white rounded-lg shadow overflow-hidden">
                    <table class="min-w-full divide-y divide-gray-200">
                        <thead class="bg-gray-50">
                            <tr>
                                <th
                                    class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                    제목</th>
                                <th
                                    class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                    접수 기간</th>
                                <th
                                    class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                    링크</th>
                            </tr>
                        </thead>
                        <!-- Data populated via JSTL -->
                        <c:forEach var="post" items="${activePosts}">
                            <tr>
                                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${post.title}
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">${post.startDate} ~
                                    ${post.endDate}</td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-blue-600 hover:text-blue-900">
                                    <a href="${post.url}" target="_blank">상세보기</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty activePosts}">
                            <tr>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500" colspan="3">현재 진행중인 공고가
                                    없습니다.</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </section>

            <!-- Section B: Crawl Logs -->
            <section>
                <h2 class="text-2xl font-bold mb-4 border-l-4 border-primary pl-3">최근 크롤링 기록</h2>
                <div class="bg-white rounded-lg shadow overflow-hidden">
                    <table class="min-w-full divide-y divide-gray-200">
                        <thead class="bg-gray-50">
                            <tr>
                                <th
                                    class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                    시간</th>
                                <th
                                    class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                    발견 공고 수</th>
                                <th
                                    class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                    목록</th>
                            </tr>
                        </thead>
                        <tbody class="bg-white divide-y divide-gray-200">
                            <c:forEach var="log" items="${crawlLogs}">
                                <tr>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">${log.crawlTime}</td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${log.activeCount}
                                    </td>
                                    <td class="px-6 py-4 text-sm text-gray-500 truncate max-w-xs"
                                        title="${log.activeTitles}">
                                        ${log.activeTitles}
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty crawlLogs}">
                                <tr>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500" colspan="3">크롤링 기록이
                                        없습니다.</td>
                                </tr>
                            </c:if>
                    </table>
                </div>
            </section>
        </div>

        <jsp:include page="../common/footer.jsp" />