json.extract! qna, :id, :title, :content, :created_at, :updated_at
json.url qna_url(qna, format: :json)
