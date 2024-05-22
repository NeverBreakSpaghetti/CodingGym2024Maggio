// https://www.hackerrank.com/challenges/cut-the-sticks

#include <algorithm>
#include <array>
#include <cassert>
#include <numeric>
#include <ranges>
#include <span>
#include <vector>

namespace {

static constexpr unsigned stick_length_count{1000};

// Note: The frequencies are offset by 1. I.e. to get the frequency of value x,
// you have to query `f[x-1]`.
//
// This doesn't affect the algorithm, in this case.
auto eval_freqs(std::vector<int> const &arr)
    -> std::array<unsigned, stick_length_count> {

    std::array<unsigned, stick_length_count> freqs{};
    for (auto const e : arr)
        ++freqs[e - 1];

    return freqs;
}

std::vector<int> cut_the_sticks(std::vector<int> const &arr) {
    auto const freqs = eval_freqs(arr);

    auto const nonZero = [](unsigned f) { return f != 0; };
    auto const nonZeroFreqCount =
        std::count_if(freqs.begin(), freqs.end(), nonZero);

    std::vector<int> res(nonZeroFreqCount + 1);
    std::copy_if(freqs.begin(), freqs.end(), res.begin() + 1, nonZero);

    auto stickCount = arr.size();
    for (auto &f : res) {
        auto const ff = f;
        f = stickCount - f;
        stickCount -= ff;
    }

    res.pop_back();
    return res;
}

std::vector<int> cut_the_sticks_ranges(std::vector<int> const &sticks) {
    namespace stdr = std::ranges;
    namespace stdv = std::views;

    auto const freqs = eval_freqs(sticks);
    auto nonZeroFreqs = freqs | stdv::filter([](auto f) { return f != 0; });

    std::vector<int> res(nonZeroFreqs.begin(), nonZeroFreqs.end());
    std::exclusive_scan(res.begin(), res.end(), res.begin(), 0, std::plus<>{});

    auto const stickCount = sticks.size();
    stdr::transform(res, res.begin(),
                    [stickCount](auto r) { return stickCount - r; });
    return res;
}

void print(std::span<int const> s) {
    for (auto e : s)
        std::printf("%d ", e);
    std::puts("");
}

} // namespace

int main() { //

    auto res = cut_the_sticks_ranges({6, 4, 2, 1});
    print(res);

    res = cut_the_sticks_ranges({1, 2, 3, 4, 3, 3, 2, 1});
    print(res);
}
