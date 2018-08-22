%===============================================================================
%
% Settings
%
%===============================================================================
DIRECTORY = "data/180821/dao";
SAVE_FILENAME = "img/results_dao.png";
PLOT_SCENARIOS = [];
RUNNING_TIMES_EXPLANATION_FILENAME = "img/running_times_explanation_dao.png";

files = dir(fullfile(DIRECTORY, "*.csv"));
if size(files, 1) == 0
    disp(["No CSV files in the directory " DIRECTORY])
    return;
end

t = zeros(1024, 7);
n = zeros(1024, 7);
max_buckets = 0;

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
    % Plot results for individual scenarios
    %
    %===========================================================================
    if ismember(i, PLOT_SCENARIOS)
        img_path = fullfile("img", "scenarios");
        mkdir img_path;
        [filepath,name,ext] = fileparts(filename);
        plot_result(i, B, fullfile(img_path, strcat(name, ".png")));
    end

    %===========================================================================
    %
    % Plot running times for scenarios with >200 buckets
    %
    %===========================================================================
    if buckets > 200
        f = figure(2);

        subplot(1, 3, 1)
        plot(b, t_D, "k"); hold on;
        title(strcat("Running times for Dijkstra (scenarios with >200 buckets)"))
        xlabel("Bucket")
        ylabel("Average time (ms)")

        subplot(1, 3, 2)
        plot(b, t_Astar, "k");
        title(strcat("Running times for A* (scenarios with >200 buckets)"))
        xlabel("Bucket")
        ylabel("Average time (ms)")

        subplot(1, 3, 3)
        plot(b, t_JPS, "k");
        title(strcat("Running times for JPS (scenarios with >200 buckets)"))
        xlabel("Bucket")
        ylabel("Average time (ms)")
    end

    %===========================================================================
    %
    % Average over scenarios
    %
    %===========================================================================
    t(1:buckets, :) += B;
    n(1:buckets, :) += 1;
    max_buckets = max(max_buckets, buckets);
end

saveas(f, RUNNING_TIMES_EXPLANATION_FILENAME);

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
plot_result(1, t_avg, SAVE_FILENAME);
