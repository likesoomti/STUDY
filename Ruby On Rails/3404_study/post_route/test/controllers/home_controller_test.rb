require 'test_helper'

class HomeControllerTest < ActionDispatch::IntegrationTest
  test "should get index" do
    get home_index_url
    assert_response :success
  end

  test "should get red" do
    get home_red_url
    assert_response :success
  end

  test "should get blue" do
    get home_blue_url
    assert_response :success
  end

  test "should get green" do
    get home_green_url
    assert_response :success
  end

  test "should get yellow" do
    get home_yellow_url
    assert_response :success
  end

end
