json.extract! fan_comment, :id, :author_no, :name, :body, :deleted, :created_at, :updated_at
json.url fan_comment_url(fan_comment, format: :json)
