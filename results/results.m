%===============================================================================
%
% Settings
%
%===============================================================================
DIRECTORY = "data/180821/dao";
SAVE_FILENAME = "img/dao_results.png";
Y_LIMITS = [[0 40]; [0 12]; [0 6]];

files = dir(fullfile(DIRECTORY, "*.csv"));
if size(files, 1) == 0
    disp(["No CSV files in the directory " DIRECTORY])
    return;
end

t = zeros(1024, 7);
n = zeros(1024, 7);
max_buckets = 0;

% Clear figures
clf(1);

for i = 1:size(files, 1)
    %===========================================================================
    %
    % Load the data
    %
    %===========================================================================
    filename = fullfile(DIRECTORY, files(i).name);
    A = load("-ascii", filename);
    %disp(["Loaded file " filename])

    % Check the number of rows and columns
    rows = size(A, 1);
    cols = size(A, 2);
    if rows == 0
        disp(["File has 0 rows: ", filename])
    end
    if cols != 7
        disp(["File should have exactly 7 columns: " filename " (has " num2str(cols) ")"])
        break;
    end

    %===========================================================================
    %
    % Average over buckets
    %
    %===========================================================================
    buckets = ceil(rows / 10);
    B = zeros(buckets, 7);
    for k = 1:buckets
        bucket_start = 1 + 10 * (k-1);
        bucket_end = min(rows, bucket_start + 9);
        bucket_data = A(bucket_start:bucket_end, :);
        B(k, :) = mean(bucket_data);
    end

    % Averaged columns
    b = B(:, 1);
    t_D = B(:, 2);
    t_Astar = B(:, 3);
    t_JPS = B(:, 4);

    %===========================================================================
    %
    % Average over scenarios
    %
    %===========================================================================
    t(1:buckets, :) += B;
    n(1:buckets, :) += 1;
    max_buckets = max(max_buckets, buckets);
end

% Drop rows which are all zeros
t = t(1:max_buckets, :);
n = n(1:max_buckets, :);

% Compute the average
% (divide by the number of scenarios)
t_avg = t ./ n;

%===========================================================================
%
% Plot the average over scenarios
%
%===========================================================================
plot_result(1, t_avg, SAVE_FILENAME, Y_LIMITS);
