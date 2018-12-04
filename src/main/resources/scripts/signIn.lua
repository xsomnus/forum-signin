local id_card_key = KEYS[1]
local serial_no_key = KEYS[2]
local sign_key = KEYS[3]


-- 判断用户是否存在
local hasIdKey = redis.call('EXISTS', id_card_key);
if hasIdKey ~= 1 then
    return 101001;
end
-- 判断用户姓名与身份证号是否匹配
local predicateUsername = redis.call('GET', id_card_key);
if predicateUsername ~= ARGV[1] then
    return 101002;
end

--- 判断用户是否已签到
local hasSigned = redis.call('EXISTS', sign_key);
if hasSigned ~= 0 then
    return 101003;
end

local serialNo = redis.call('INCR', serial_no_key);

redis.call('HSET', sign_key, 'serialNo', serialNo);
redis.call('HSET', sign_key, 'idCard', ARGV[2]);
redis.call('HSET', sign_key, 'signTime', ARGV[3]);
redis.call('HSET', sign_key, 'name', ARGV[1]);

return 0;
