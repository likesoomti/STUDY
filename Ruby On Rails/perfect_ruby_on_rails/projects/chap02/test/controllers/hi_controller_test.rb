require 'test_helper'

class HiControllerTest < ActionDispatch::IntegrationTest
  test "should get new" do
    get hi_new_url
    assert_response :success
  end

  test "should get show" do
    get hi_show_url
    assert_response :success
  end

  test "should get destroy" do
    get hi_destroy_url
    assert_response :success
  end

end
