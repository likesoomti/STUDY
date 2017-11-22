require 'test_helper'

class QnAsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @qna = qnas(:one)
  end

  test "should get index" do
    get qnas_url
    assert_response :success
  end

  test "should get new" do
    get new_qna_url
    assert_response :success
  end

  test "should create qna" do
    assert_difference('Qna.count') do
      post qnas_url, params: { qna: { content: @qna.content, title: @qna.title } }
    end

    assert_redirected_to qna_url(Qna.last)
  end

  test "should show qna" do
    get qna_url(@qna)
    assert_response :success
  end

  test "should get edit" do
    get edit_qna_url(@qna)
    assert_response :success
  end

  test "should update qna" do
    patch qna_url(@qna), params: { qna: { content: @qna.content, title: @qna.title } }
    assert_redirected_to qna_url(@qna)
  end

  test "should destroy qna" do
    assert_difference('Qna.count', -1) do
      delete qna_url(@qna)
    end

    assert_redirected_to qnas_url
  end
end
