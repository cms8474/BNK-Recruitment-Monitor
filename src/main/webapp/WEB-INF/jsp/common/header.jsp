<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BNK Recruitment Monitor</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: '#E34234',
                        text: '#333333'
                    }
                }
            }
        }
    </script>
</head>
<body class="bg-gray-50 text-text">
    <nav class="bg-white border-b-2 border-primary shadow-sm">
        <div class="container mx-auto px-6 py-4 flex justify-between items-center">
            <a href="/" class="text-2xl font-bold text-primary">BNK Monitor</a>
            <div class="space-x-4">
                <a href="/" class="text-gray-600 hover:text-primary font-medium">Home</a>
                <a href="/recruit" class="text-gray-600 hover:text-primary font-medium">Recruitment</a>
            </div>
        </div>
    </nav>
    <main class="container mx-auto px-6 py-8 min-h-screen">
