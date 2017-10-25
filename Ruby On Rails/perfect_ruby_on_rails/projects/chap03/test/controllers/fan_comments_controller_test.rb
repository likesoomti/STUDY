require 'test_helper'

class FanCommentsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @fan_comment = fan_comments(:one)
  end

  test "should get index" do
    get fan_comments_url
    assert_response :success
  end

  test "should get new" do
    get new_fan_comment_url
    assert_response :success
  end

  test "should create fan_comment" do
    assert_difference('FanComment.count') do
      post fan_comments_url, params: { fan_comment: { author_no: @fan_comment.author_no, body: @fan_comment.body, deleted: @fan_comment.deleted, name: @fan_comment.name } }
    end

    assert_redirected_to fan_comment_url(FanComment.last)
  end

  test "should show fan_comment" do
    get fan_comment_url(@fan_comment)
    assert_response :success
  end

  test "should get edit" do
    get edit_fan_comment_url(@fan_comment)
    assert_response :success
  end

  test "should update fan_comment" do
    patch fan_comment_url(@fan_comment), params: { fan_comment: { author_no: @fan_comment.author_no, body: @fan_comment.body, deleted: @fan_comment.deleted, name: @fan_comment.name } }
    assert_redirected_to fan_comment_url(@fan_comment)
  end

  test "should destroy fan_comment" do
    assert_difference('FanComment.count', -1) do
      delete fan_comment_url(@fan_comment)
    end

    assert_redirected_to fan_comments_url
  end
end
